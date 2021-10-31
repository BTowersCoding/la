(ns la.app
  (:require [reagent.core :as r]
            [reagent.dom :as rdom]))

(defn app []
  [:div#app
   [:h1 "Linear Algebra with Clojurescript"]])

(defn render []
  (rdom/render [app]
            (.getElementById js/document "root")))

(defn ^:dev/after-load start []
  (render)
  (js/console.log "start"))

(defn ^:export init []
  (js/console.log "init")
  (start))

(defn ^:dev/before-load stop []
  (js/console.log "stop"))