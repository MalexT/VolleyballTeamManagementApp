(ns volleyball-team-management.models.team
  (:refer-clojure :exclude [get update])
  (:require [clojure.java.jdbc :as jdbc]
            [clojure.java.jdbc.sql :as sql]
            [clojure.edn :as edn]))

(def db (edn/read-string (slurp "config/db-config.edn")))

(defn allTeams []
  (jdbc/query db
              ["SELECT t.name,
                l.name AS league,
                t.city,
                t.STATE,
                t.foundationDate
                FROM teams t
                JOIN league l ON t.leagueId = l.leagueId"]))

(defn deleteTeam [id]
  (jdbc/delete! db :teams (sql/where {:teamId id})))

(defn get [id]
  (first (jdbc/query db 
                     (sql/select * :teams (sql/where {:teamId id})))))

(defn update [id parameters]
  (jdbc/update! db :teams parameters (sql/where {:teamId id})))

(defn insertTeam [parameters]
  (jdbc/insert! db :teams parameters))

(defn searchTeam [parameters]
  (jdbc/query db 
              [(str "SELECT t.name,
                l.name AS league,
                t.city,
                t.STATE,
                t.foundationDate
                FROM teams t
                JOIN league l ON t.leagueId = l.leagueId
                WHERE " parameters)]))