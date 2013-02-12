(ns json-to-mongo.app
	(require [clojure.data.json :as json]
			 [monger.core :as mg])
	(use [clojure.tools.cli :only[cli]]
		 [monger.core :only [connect! connect set-db! get-db]]
        [monger.collection :only [insert]])
	(import [com.mongodb MongoOptions ServerAddress]) 
  )

(defn run 
  [opts args]
  (let [jsonfile (json/read-str (slurp (nth args 0)) :key-fn keyword)
  		ClientID (nth args 1)]

  		(mg/connect! {:host (opts :url) :port (opts :port)})
  		(set-db! (monger.core/get-db (opts :database)))

  		(doseq [SDDat (get-in jsonfile [:SDData])
  		      :let [dat (get-in SDDat [:Data])
  		      		Products (get-in dat [:Products])]]

  		      		(doseq [Prod Products
  		      			:let [sp (assoc (get Prod :Product) "ClientID" ClientID)]]
  		     
  		      				(insert (opts :collection) sp)))))

(defn -main 
  [& args]
  (let [[opts args banner]
		(cli args 
			["-u" "--url" "The URL or IP address to access Mongodb" :default "127.0.0.1"]
			["-p" "--port" "The port to use to connect to Mongodb" :default 27017]
			["-db" "--database" "The databse where the collection is stored"]
			["-c" "--collection" "The collection that the data will be imported too"]
			["-h" "--help" "Show help" :flag true :default false])]

			(when (:help opts)
				(println "Default usage: J2M <options> <args>; The first argument must be the location of the JSON file.")
				(println banner)
				(println "\n")
				(System/exit 0))

		  	(do 
		  		(println "")
		  		(run opts args))))

