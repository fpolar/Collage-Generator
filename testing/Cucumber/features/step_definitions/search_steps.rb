Given(/^I am on the welcome page$/) do
  visit "http://localhost:8080/Collage/"
end

Then(/^the background should be light\-gray$/) do
  color = find('body').native.css_value('background-color')
  expect(color).to eq("rgba(211, 211, 211, 1)")
end 

#Tests above this line should work 

#I think we forgot to implement this
Then(/^the input box should have a dark\-gray outline$/) do
  outline = find('#search-bar').native.css_value('border-color')
  expect(outline).to eq("rgba(169,169,169,1)")
end

Then(/^the input box is centered in the middle of the screen$/) do
  search_bar_position = find('#search-bar').native.css_value('position')
  search_bar_top = find('#search-bar').native.css_value('top')
  search_bar_left = find('#search-bar').native.css_value('left')
  expect(search_bar_postion).to eq("relative")
  expect(search_bar_top).to eq("46vh")
  expect(search_bar_top).to eq("37.5vw")
end

Then(/^the input box has prompt text "([^"]*)"$/) do |arg1|
  prompt_text = find("input-field").placeholder
  expect(prompt_text).to eq("Enter Topic")
end

Then(/^the prompt text is in a light gray color$/) do
  search_bar_text_color = find("input-field").native.css_value('text-color')
  expect(outline).to eq("rgba(211, 211, 211, 1)")
end




