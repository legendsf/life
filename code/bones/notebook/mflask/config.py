HOSTNAME='dohko.mysql.001.master.hualala.com'
PORT='3306'
DATABASE ='db_luban'
USERNAME='root'
PASSWORD='hualaladev'
DB_URI='mysql+mysqldb://{}:{}@{}:{}/{}?charset=utf8'.format(USERNAME,PASSWORD,HOSTNAME,PORT,DATABASE)
SQLALCHEMY_DATABASE_URI =DB_URI
SQLALCHEMY_TRACK_MODIFICATIONS=True

print(DB_URI)