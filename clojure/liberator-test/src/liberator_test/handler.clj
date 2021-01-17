(ns liberator-test.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [liberator.core :refer [resource defresource]]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defroutes app-routes
  (GET "/" [] (resource :available-media-types ["text/html"]
                        :handle-ok (fn [ctx]
                                     (format "<html>It's %d milliseconds since the beginning of the epoch."
                                             (System/currentTimeMillis)))))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
