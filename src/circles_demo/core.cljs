(ns circles-demo.core
  (:require
    [quil.core :as q]
    [quil.middleware :as m]
    [reagent.core :as r]))

(defn draw [{:keys [circles]}]
  (q/background 255)
  (doseq [{[x y] :pos [r g b] :color} circles]
    (q/fill r g b)
    (q/ellipse x y 10 10)))

(defn update-state [{:keys [width height] :as state}]
  (update state :circles conj {:pos   [(+ 20 (rand-int (- width 40)))
                                       (+ 20 (rand-int (- height 40)))]
                               :color (repeatedly 3 #(rand-int 250))}))

(defn init [width height]
  (fn []
    {:width   width
     :height  height
     :circles []}))

(defn canvas []
  (r/create-class
    {:component-did-mount
     (fn [component]
       (let [node (r/dom-node component)
             width (/ (.-innerWidth js/window) 2)
             height (/ (.-innerHeight js/window) 2)]
         (q/sketch
           :host node
           :draw draw
           :setup (init width height)
           :update update-state
           :size [width height]
           :middleware [m/fun-mode])))
     :render
     (fn [] [:div])}))

(defn home-page []
  (r/with-let [running? (r/atom false)]
    [:div
     [:h3 "circles demo"]
     [:div>button
      {:on-click #(swap! running? not)}
      (if @running? "stop" "start")]
     (when @running?
       [canvas])]))

(defn mount-root []
  (r/render [home-page] (.getElementById js/document "app")))

(defn init! []
  (mount-root))
