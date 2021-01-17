var BACKEND_URL = 'https://hm2n9sq7nc.execute-api.eu-west-2.amazonaws.com/dev/snippets';

var snippetsForm = new Vue({
  el: '#snippets-form',
  data: {
    text: null
  },
  methods: {
    createSnippet: function() {
      alert(`Creating snippet: ${this.text}`);
      axios.post(BACKEND_URL, {
        "text": this.text
      })
      .then(function (response) {
        self.snippets = self.snippets + response["data"];
      })
      .catch(function (error) {
        alert('createSnippet error');
      });
    }
  }
})

var snippetsList = new Vue({
  el: '#snippets-list',
  data: {
    snippets: []
  },
  mounted: function () {
    var self = this;
    axios.get(BACKEND_URL)
      .then(function (response) {
        self.snippets = response["data"];
      })
      .catch(function (error) {
        alert('snippetList mount error');
      });
  },
  methods: {
    deleteSnippet: function(snippet) {
      var self = this;
      alert(`Deleting snippet ${snippet.id}`);
      axios.delete(`${BACKEND_URL}/${snippet.id}`)
        .then(function (response) {
          self.snippets = self.snippets.filter(item => item !== snippet);
        })
        .catch(function(error) {
          alert('deleteSnippet error');
        });
    },
  }
})