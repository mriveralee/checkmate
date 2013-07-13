User = require('../models/user')

#
# * GET Users listing.
#
exports.list = (req, res) ->
  res.send "respond with a resource"

exports.json_all = (req, res) ->
    User.find().exec (err, results) ->
        if(!res.headerSent)
          res.json(results)

exports.register_post = (req, res) ->
  # res.render "hi"
  console.log req.body
  new User(req.body).save (err) ->
    if err then console.log 'ERROR'
    else console.log 'yeaaah'

exports.register_get = (req, res) ->
  res.render "register"

exports.login_post = (req, res) ->
  User.findOne email: req.body.email, (err, result) ->
    if result and result.authenticate(req.body.password)
      # set session User id
      req.session.User_id = result._id
      console.log 'logged in!'
      console.log req.session.User_id
      res.redirect '/'
    else
      console.log 'incorrect password'
      res.redirect 'login'

exports.login_get = (req, res) ->
  console.log 'reached here'
  res.render "login"

exports.get_menu = (req, res) =>
  # TODO: correct conditioning on email
  User.findOne req.params.email, (err, restaurant) ->
    res.send restaurant.menu

exports.get_user = (req, res) =>
  # TODO: correct conditioning on email
  User.findOne req.params.email, (err, restaurant) ->
    res.send restaurant
