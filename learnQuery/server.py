import threading
import webbrowser
import BaseHTTPServer
import SimpleHTTPServer

FILE = 'frontend.html'
PORT = 9999

import json

import harnessGenerator

class TestHandler(SimpleHTTPServer.SimpleHTTPRequestHandler):
    """
    def do_POST(self):     
        length = int(self.headers.getheader('content-length'))        
        data_string = self.rfile.read(length)
        try:
            result = int(data_string) ** 2
        except:
            result = 'error'
        self.wfile.write(result)
        
        print "Received", repr(self)     
    """
    
    def do_GET(self):
        #print "Received", repr(self)
        print "received:", self.path
        params = self.path.split("&")

        numCols = params[0].split("@")[1]
        data = params[1].split("@")[1].split(",")
        print "received numCols:", numCols, "data:", data 
        
        result = harnessGenerator.processRequest(data, numCols)

        self.send_response(200, 'OK')
        self.send_header('Content-type', 'application/json')
        self.send_header('Access-Control-Allow-Origin', '*');
        self.end_headers()

        """
        eventListDict = {'events': '123'}    
        s = json.dumps(eventListDict)
        
        fname = self.path.split("=")
        fname = fname[1].split("&")[0]
        print fname
        self.wfile.write(fname + '(' + s + ')')
        """

        self.wfile.write(result)



def open_browser():
    """Start a browser after waiting for half a second."""
    def _open_browser():
        webbrowser.open('http://localhost:%s/%s' % (PORT, FILE))
    thread = threading.Timer(0.5, _open_browser)
    thread.start()

def start_server():
    """Start the server."""
    server_address = ("", PORT)
    server = BaseHTTPServer.HTTPServer(server_address, TestHandler)
    server.serve_forever()

if __name__ == "__main__":
    #open_browser()
    start_server()
