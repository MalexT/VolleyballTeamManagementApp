(ns volleyball-team-management.models.position
  (:refer-clojure :exclude [get update])
  (:require [clojure.java.jdbc :as jdbc]
            [clojure.java.jdbc.sql :as sql]))

(def db (clojure.edn/read-string (slurp "config/db-config.edn")))

(defn allPositions []
  (jdbc/query db
              ["SELECT * FROM position"]))