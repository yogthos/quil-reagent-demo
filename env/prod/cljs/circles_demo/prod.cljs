(ns circles-demo.prod
  (:require
    [circles-demo.core :as core]))

;;ignore println statements in prod
(set! *print-fn* (fn [& _]))

(core/init!)
