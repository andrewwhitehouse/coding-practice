(ns liberator-test.handler-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [liberator-test.handler :refer :all]))

(deftest test-app
  (testing "main route"
    (let [response (app (mock/request :get "/"))]
      (is (= (:status response) 200))
      (is (not (nil? (re-find  #"<html>It's \d+ milliseconds since the beginning of the epoch." (:body response)))))))

  (testing "not-found route"
    (let [response (app (mock/request :get "/invalid"))]
      (is (= (:status response) 404)))))
