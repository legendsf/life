from threading import Thread
from flask import Flask
from flask_mail import Mail,Message

class Config(object):
    MAIL_SERVER='mail.hualala.com'
    MAIL_PORT=465
    MAIL_USE_SSL=True
    MAIL_USERNAME='songfei@hualala.com'
    MAIL_PASSWORD='SFgd@123'

app=Flask(__name__)
app.config.from_object(Config)

mail=Mail(app)

@app.route('/send_mail')
def send_mail():
    message=Message("标题",sender='songfei',recipients=['623667783@qq.com'])
    message.body="""
    HI,songfei,你好
        goodluck !
    """
    t=Thread(target=send_mail,args=(message,))
    t.start()
    return "发送成功"

def send_mail(msg):
    with app.app_context():
        mail.send(msg)

if __name__ == '__main__':
    app.run()