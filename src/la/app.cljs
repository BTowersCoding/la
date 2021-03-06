(ns la.app
  (:require [reagent.core :as r]
            [reagent.dom :as rdom]
            [la.editor :as editor :refer [update-editor! !tri eval-all]]
            [la.math :refer [pi]]
            [la.vectors :refer []]))

(defonce tri
  (r/atom {:magnitude 4 :direction 80}))

(def view-box-width 400)
(def view-box-height 400)

(defn grid []
    [:path {:d "M0 400V0M25 400V0M50 400V0M75 400V0M100 400V0M125 400V0M150 400V0M175 400V0
                M200 400V0M225 400V0M250 400V0M275 400V0M300 400V0M325 400V0M350 400V0M375 400V0M400 400V0
                M0 400h400M0 375h400M0 350h400M0 325h400M0 300h400M0 275h400M0 250h400M0 225h400M0 200h400
                M0 175h400M0 150h400M0 125h400M0 100h400M0 75h400M0 50h400M0 25h400M0 0h400"
            :stroke "#ffcc00" :stroke-width 1 :opacity ".1"}])

(defn axes []
  [:path {:d "M0 200h400 M200 1.05v400"
          :stroke "#ffcc00" :stroke-linejoin "round"
          :stroke-linecap "round" :stroke-width 1}])

(defn arrows []
  [:path {:d "M6 194.4c-.35 2.1-4.2 5.25-5.25 5.6 1.05.35 4.9 3.5 5.25 5.6
              M394.45 205.6c.35-2.1 4.2-5.25 5.25-5.6-1.05-.35-4.9-3.5-5.25-5.6
              M194.5 393.7c2.1.35 5.25 4.2 5.6 5.25.35-1.05 3.5-4.9 5.6-5.25
              M205.5 6.3c-2.1-.35-5.25-4.2-5.6-5.25-.35 1.05-3.5 4.9-5.6 5.25"
          :fill "none" :stroke "#ffcc00"
          :stroke-linejoin "round" :stroke-linecap "round" :stroke-width 1}])

(defn ticks []
  [:path {:d "M225 205v-10M250 205v-10M275 205v-10M300 205v-10M325 205v-10M350 205v-10M375 205v-10
              M175 205v-10M150 205v-10M125 205v-10M100 205v-10M75 205v-10M50 205v-10M25 205v-10
              M195 175h10M195 150h10M195 125h10M195 100h10M195 75h10M195 50h10M195 25h10
              M195 225h10M195 250h10M195 275h10M195 300h10M195 325h10M195 350h10M195 375h10"
          :stroke "#ffcc00"}])

(defn v [x y a]
  [:g [:path {:d (str "M" (+ 195.5 x) " " (+ 205.5 y)
                      "c.35-2.1 4.2-5.25 5.25-5.6-1.05-.35-4.9-3.5-5.25-5.6")
              :transform (str "rotate(" a " " (+ x 200) " " (+ y 200) ")")
              :stroke "#61e2ff" :fill "none"}]
   [:path {:d (str "M200 200l" x " " y)
           :stroke "#61e2ff" :fill "none"}]])

(defn graph [x1 y1 x2 y2]
  [:svg {:width "100%" :view-box (str "0 0 " view-box-width " " view-box-height)}
   [:g [grid] [arrows] [axes] [ticks]
    [v (* x1 25) (* y1 -25) (- -180 (* (/ 180 3.141592653589) (.atan js/Math (/ y1 x1))))]
    [v (* x2 25) (* y2 -25) (- (* (/ 180 3.141592653589) (.atan js/Math (/ y2 x2))))]]])

(defn app []
  [:div#app
   [:h2 "Linear Algebra"]
   [editor/editor (str @tri) !tri {:eval? true}]
   [graph -3 6 3 2]])

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
