(ns rna-transcription)

(defn to-rna [dna]
  (let [rna (mapv {\G \C, \T \A, \C \G, \A \U} dna)]
    (assert (not-any? nil? rna))
    (apply str rna)))
