# Simple class
class Person
  constructor: (name, email) -> 
    @name = name
    @email = email
  name: 'Anonymous'
  email: null
  sayHello: -> console.log "My name is #{@name}!" 

bob = new Person('Bob')
bob.sayHello()

# Class Inheritancea
###
class Programmer extends Person
  hack: (it) -> console.log "Hack the #{it}!"

chris = new Programmer('Chris')
chris.sayHello()
chris.hack('CoffeeScript')
###

# Simple function
echo = (v) -> console.log v
echo "foo"

twice = (f, v) -> f(v) for [1..2]
twice(echo, "bar")

