<?php 

/**
 * 往网络端口写入、读取信息
 */
$host = '192.168.184.199';
$port = 9501;

$f = fsockopen($host, $port, $errno, $errstr, 1);

if ($errno != 0) {
	echo $errstr . PHP_EOL;
	exit;
}

// 比如说netcat、swoole的tcp server
fwrite($f, "hello\n");

// 接受返回
$str = '';
while (!myeof($str)) {
	$str .= fread($f, 10);
}
$str = substr($str, 0, - strlen("\n"));
var_dump($str);

function myeof($str) {
	return substr($str, - strlen("\n")) == "\n";
}

/* swoole tcp server 示例
class TcpServer {
    private $server;
    private $fdArr = [];
    private $set = [
        'open_eof_check' => true,
        'open_eof_split' => true,
        'package_eof' => "\n"

    ];

    function __construct() {
        $this->server = new Swoole\Server('0.0.0.0', 9501);
        $this->server->set($this->set);
        $this->server->on('connect', [$this, 'connect']);
        $this->server->on('receive', [$this, 'receive']);
        $this->server->on('close', [$this, 'close']);
        $this->server->start();
    }

    function connect($server, $fd) {
        array_push($this->fdArr, $fd);
    }

    function receive($server, $fd, $reactor_id, $data) {
        foreach($this->fdArr as $v) {
            $this->server->send($v, "world\n");
        }
    }

    function close($server, $fd) {
        $this->fdArr = array_diff($this->fdArr, [$fd]);
    }
}
new TcpServer();
 */