util = require 'util'
User = require "./models/user"

default_user =
  email: 'admin'
  password: 'admin'
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
  User.create default_user, (err, user) ->
    console.log err if err?
    console.log 'Filled fake data'
    console.log util.inspect user