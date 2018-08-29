(ns ^:figwheel-no-load circles-demo.dev
  (:require
    [circles-demo.core :as core]
    [devtools.core :as devtools]))


(enable-console-print!)

(devtools/install!)

(core/init!)
