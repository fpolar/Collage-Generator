Given(/^I am on the welcome page$/) do
  visit "http://localhost:8080/Collage/"
end

Then(/^the background should be light\-gray$/) do
  expect(page.css('body').css('background-color')).to eq("LightGrey") or eq("LightGray") or eq("#D3D3D3")
end

