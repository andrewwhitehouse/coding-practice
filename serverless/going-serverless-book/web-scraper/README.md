# web-scraper

# Sample request for scrapes

```
{ 
  "url" : "https://www.bbc.co.uk/news/uk-54944125" , 
  "mapping" : {  
    "title" : "#main-heading"
  }  
}
```

# Sample for crawl

```
{ 
  "url" : "https://www.jobserve.com/gb/en/Listings/Recruiters/IntecSelect/Jobs/?shid=91E292C34B7836E8D213&ac=FC21BD9D9092D68D&src=E395E5BB42CB2AE40771160DC5C9829E848D" , 
  "linkSelector" : ".jobListItem a[href]",
  "mapping" : {  
    "position" : "#td_jobpositionnolink",
    "salary": "#td_location_salary"
  }  
}
```