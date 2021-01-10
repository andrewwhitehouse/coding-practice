(ns codewars.max-sequence)

;; https://www.codewars.com/kata/54521e9ec8e60bc4de000d6c/train/clojure


(defn max-sequence [xs]
  (if (= xs (sort xs))
    (apply + xs)
    (let [num-xs (count xs)
          sum (fn [xs from to]
                (loop [total 0 idx from]
                  (if (<= idx to)
                    (recur (+ total (nth xs idx)) (inc idx))
                    total)))]
      (->> (for [to (range num-xs)
                 from (range (inc to))]
             (sum xs from to))
           (apply max 0)))))

;; gent
(defn max-sequence-gent
  [xs]
  (apply max (reductions #(max (+ %1 %2) 0) 0 xs)))