util = require 'util'
Restaurant = require "./models/restaurant"

default_restaurant =
  address_1: '120 2nd Street' 
  city: 'Mountain View' 
  state: 'CA'
  phone: '415-555-9815'
  menu: 
    'Boont Amber Ale': 5
    'Long Cooked Pork Shoulder': 25
    'Rack of Lamb': 22 
    'Steak Tartare': 28 
    'Sliced Raw Yellowfin Tuna': 15
    'Mushroom Risotto': 18
    'Cheesecake': 6

exports.run = () ->
  Restaurant.create default_restaurant, (err, restaurant) ->
    console.log err if err?
    console.log 'Filled fake data'
    console.log util.inspect restaurant