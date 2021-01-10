(ns codility.equi)

(defn equi [xs]
  (let [xs (vec xs)
        num-xs (count xs)]
    (loop [sum-left 0
           sum-right (apply + (long (first xs)) (rest xs))
           idx 0
           acc []]
      (if (< idx num-xs)
        (let [x (nth xs idx)
              left (+ sum-left x)
              right (- sum-right x)]
          (if (= (- left x) right)                          ; exclude current element
            (recur left right (inc idx) (conj acc idx))
            (recur left right (inc idx) acc)))
        acc))))
