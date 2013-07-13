util = require 'util'
User = require "./models/user"
async = require 'async'

default_user =
  email: 'test@test.com'
  password: 'test'
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
async.waterfall [
  (cb_wf) -> 
    User.remove {}, cb_wf
  (cb_wf) ->
    default_user =
      email: 'test@test.com'
      password: 'test'
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
    User.create default_user, (err, user) ->
      console.log err if err?
      console.log 'Filled fake data'
      console.log util.inspect user
], (err) ->
  console.log err if err?
