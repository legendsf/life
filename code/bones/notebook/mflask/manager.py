from flask_migrate import Manager,Migrate,MigrateCommand
from migrate_demo import app
from exts import db
from models import Article

manager=Manager(app)
migrate=Migrate(app,db)
manager.add_command('db',MigrateCommand)

#python manager.py db init
#python manager.py db migrate
#python manager.py db upgrade


if __name__ == '__main__':
    manager.run()

