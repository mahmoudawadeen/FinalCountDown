json.array!(@tests) do |test|
  json.extract! test, :id, :text
  json.url test_url(test, format: :json)
end
