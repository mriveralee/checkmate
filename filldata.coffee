util = require 'util'
User = require "./models/user"
async = require 'async'


default_user =
  name: 'SomeThin Wong'
  email: 'test@test.com'
  venmo_id: 'kevinshen'
  password: 'test'
  address: '120 2nd Street' 
  city: 'Mountain View' 
  state: 'CA'
  zip_code: '94121'
  phone: '415-555-9815'
  menu: 
    'Boont Amber Ale': '5.50'
    'Long Cooked Pork Shoulder': '25.00'
    'Rack of Lamb': '22.25'
    'Steak Tartare': '28.00'
    'Sliced Raw Yellowfin Tuna': '15.50'
    'Mushroom Risotto': '18.00'
    'Cheesecake': '6.00'

exports.run = () ->
async.waterfall [
  (cb_wf) -> 
    User.remove {}, cb_wf
  (cb_wf) ->
    User.create default_user, (err, user) ->
      console.log err if err?
      console.log 'Filled fake data'
      console.log util.inspect user
], (err) ->
  console.log err if err?
