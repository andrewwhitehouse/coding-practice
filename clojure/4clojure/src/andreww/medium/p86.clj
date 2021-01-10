(ns andreww.medium.p86)

(def __
  (fn [n]
    (let [digit-seq (fn digits [digs n] (if (zero? n) digs (digits (cons (rem n 10) digs) (quot n 10) )))]
      (loop [current n
             seen #{}]
        (cond
          (seen current) false
          (= current 1) true
          :else (recur (->> current (digit-seq []) (map #(* %1 %1)) (apply +)) (conj seen current)))))))

(and
  (= (__ 7) true)
  (= (__ 986543210) true)
  (= (__ 2) false)
  (= (__ 3) false))