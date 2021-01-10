(ns andreww.medium.p74)

(def __ 
  (fn [s]
    (let [perfect-square? (fn [n] 
                            ((fn [n sr] (= n (* sr sr))) 
                             n 
                             (int (Math/sqrt n))))]
      (clojure.string/join 
        "," 
        (filter 
          #(perfect-square? (Integer/parseInt %1)) 
          (clojure.string/split s #",")))))   )

(and
  (= (__ "4,5,6,7,8,9") "4,9")
  (= (__ "15,16,25,36,37") "16,25,36") )

; Other solutions ...
; austintaylor
(fn [s]
  (apply str (interpose ","
                        (filter #(let [root (Math/sqrt %)] (= root (int root)))
                                (map #(Integer/parseInt %) (.split s ","))))))

; nikelandjelo
(fn perf-sq [text]
    (let [perf-sq? (fn [x]
                         (let [rt (int (Math/sqrt x))]
                                 (= x (* rt rt))))]
          (->> (re-seq #"\d+" text)
                        (map #(Integer/parseInt %))
                        (filter perf-sq?)
                        (interpose \,)
                        (apply str))))
