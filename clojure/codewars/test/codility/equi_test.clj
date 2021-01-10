(ns codility.equi-test
  (:require [clojure.test :refer :all]
            [codility.equi :refer :all]))

(deftest simple
  (is (= [1 3 7] (equi [-1 3 -4 5 1 -6 2 1]))))

(deftest long-list
  (let [xs (repeatedly 49999 #(rand-int Integer/MAX_VALUE))]
    (is (some #{49999} (equi (concat xs [1] (shuffle xs)))))))

