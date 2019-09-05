
from flask import Flask
from exts import db
import config
# from models import Article

app=Flask(__name__)
app.config.from_object(config)
db.init_app(app)

# with app.app_context():
#     db.create_all()


@app.route('/')
def hello_world():
    return "hello world!"

if __name__ =='__main__':
    app.run(debug = True)