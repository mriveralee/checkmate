Restaurant = require "#{__dirname}/../models/restaurant"

exports.get_menu = (req, res) =>
  Restaurant.findOne req.params.id, (err, restaurant) ->
    res.send restaurant.menu

exports.get_restaurant = (req, res) =>
  Restaurant.findOne req.params.id, (err, restaurant) ->
    res.send restaurant
