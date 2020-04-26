<?php

/**
 * php下载网络资源，低耗内存，防止内存溢出
 */
class DownloadFile {

    private $header = '';

    private $size = 1024;

    private $headerSplit = "\r\n\r\n";

	public function download($url, $path, $port = 80, $timeout = 30) {
		$info = parse_url($url);
        $fSock = fsockopen($info['host'], $port, $errno, $errstr, $timeout);
        if (!$fSock) {
            echo "$errstr ($errno)" . PHP_EOL;
        } else {
            $out = "GET " . $info['path'] . " HTTP/1.1" . PHP_EOL;
            $out .= "Host: " . $info['host'] . PHP_EOL;
            $out .= "Connection: Close" . PHP_EOL . PHP_EOL;
            fwrite($fSock, $out);
            $fDes = fopen($path, "a");
            while (!feof($fSock)) {
            	$response = fgets($fSock, $this->size);

            	// 除去相应头
                $str = $this->removeHeader($response);

                fwrite($fDes, $str);

            }
            fclose($fDes);
            fclose($fSock);
        }
	}

	private function removeHeader($response) {
        if ($this->header !== false) {
            $this->header .= $response;
            $pos = strpos($this->header, $this->headerSplit);
            if ($pos !== false) {
                $str = substr($this->header, $pos + 4);
                $this->header = false;
            } else {
                $str = '';
            }
            return $str;
        } else {
            return $response;
        }
    }

}

ini_set("memory_limit", "2M");
$obj = new DownloadFile();
$obj->download('http://archive.apache.org/dist/hadoop/common/hadoop-2.7.2/hadoop-2.7.2.tar.gz', 'D:/hadoop-2.7.2.tar.gz');
