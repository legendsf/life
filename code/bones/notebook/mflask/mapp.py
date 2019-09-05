from flask import Flask
from flask_sqlalchemy import SQLAlchemy
import config
from exts import db
from flask_migrate import Migrate
from models import Article
from flask_script import Command,Manager,Shell


app=Flask(__name__)
app.config.from_object(config)
db.init_app(app)

manager=Manager(app)


class Hello(Command):
    def run(self):
        print("hello world")
manager.add_command('hello',Hello())

@manager.command
def hello1():
    print("hello1 world")

@manager.option('-n','--name',dest='name',help='your name',default='world')
@manager.option('-u','--url',dest='url',default='www.csdn.com')

def hello2(name,url):
    print('hello',name)
    print(url)

def make_shell_context():
    # return dict(app=app,db=db,Article=Article)
    return {'app':app,'db':db,'Article':Article}

manager.add_command('shell',Shell(make_context=make_shell_context))

@app.route("/")
def hello():
    return "hello world!"

if __name__ =='__main__':
    manager.run()


