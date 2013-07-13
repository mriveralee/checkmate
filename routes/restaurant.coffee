Restaurant = require "#{__dirname}/../models/restaurant"

@menu =
  'Boont Amber Ale': 5
  'Long Cooked Pork Shoulder': 25
  'Rack of Lamb': 22 
  'Steak Tartare': 28 
  'Sliced Raw Yellowfin Tuna': 15
  'Mushroom Risotto': 18
  'Cheesecake': 6

exports.get_menu = (req, res) =>
  Restaurant.findOne req.params.id, (err, restaurant) ->
    console.log restaurant
  console.log 'return', @menu
  res.send @menu