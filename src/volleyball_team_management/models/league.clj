(ns volleyball-team-management.models.league
  (:refer-clojure :exclude [get update])
  (:require [clojure.java.jdbc :as jdbc]
            [clojure.java.jdbc.sql :as sql]))

(def db (clojure.edn/read-string (slurp "config/db-config.edn")))

(defn allLeagues []
  (jdbc/query db
              ["SELECT * FROM league"]))