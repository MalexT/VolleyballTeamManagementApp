(defproject volleyball-team-management "0.1.0-SNAPSHOT"
  :description "Volleyball team management web application for faculty purposes"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [compojure "1.5.2"]
                 [org.clojure/java.jdbc "0.6.1"]
                 [mysql/mysql-connector-java "8.0.16"]]
  :plugins []
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
