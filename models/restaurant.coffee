mongoose = require 'mongoose'
Schema = mongoose.Schema

Restaurant = new Schema
  address_1: String
  address_2: String
  city: String
  state: String
  phone: String
  menu: Schema.Types.Mixed
  email: 

module.exports = mongoose.model 'Restaurant', Restaurant
