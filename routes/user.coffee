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
  new User(req.body).save (err, user) ->
    if err
      console.log 'ERROR in register'
      res.render "register"
    else
      console.log 'user created succesfully'
      console.log user
      return res.redirect "/"

exports.register_get = (req, res) ->
  res.render "register"

exports.login_post = (req, res) ->
  User.findOne email: req.body.email, (err, result) ->
    if result and result.authenticate(req.body.password)
      # set session User id
      req.session.User_id = result._id
      console.log 'logged in!'
      console.log req.session.User_id
      res.send result
    else
      console.log 'incorrect password'
      res.send {err: "incorrect password"}

exports.login_get = (req, res) ->
  return res.render "login" if not req.session.user_id
  res.render "index"

exports.get_user = (req, res) =>
  # TODO: correct conditioning on email
  id = req.session.user_id
  return res.send {err: "You must login first"} unless id?
  User.findOneById id, (err, user) ->
    return res.send user.menu
