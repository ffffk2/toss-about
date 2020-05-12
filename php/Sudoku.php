<?php

class Sudoku
{
    public function run(){
        $s = time();
        $this->print_sudoku();
        try {
            $this->fill_sudoku($this->sudoku, 0, 0);
        } catch (\Exception $e) {
            $this->print_sudoku();
        }
        var_dump(time() - $s);
    }

    private function fill_sudoku(&$sudoku, $i, $j)
    {
        if ($sudoku[$i][$j] == 0) {
            $flag = false;
            for ($num = 1; $num <= 9; $num++) {
                if ($this->can_fill($sudoku, $i, $j, $num)) {
                    $sudoku[$i][$j] = $num;
                    $flag = $this->fill_sudoku($sudoku, $this->next_i($i, $j), $this->next_j($i, $j));
                }
            }
            if (!$flag) {
                $sudoku[$i][$j] = 0;
            }
            return $flag;
        } else {
            return $this->fill_sudoku($sudoku, $this->next_i($i, $j), $this->next_j($i, $j));
        }
    }

    private function can_fill($sudoku, $i, $j, $num) {
        for ($index = 0; $index < 9; $index++) {
            // 横
            if ($sudoku[$i][$index] == $num) return false;
            // 竖
            if ($sudoku[$index][$j] == $num) return false;
        }
        // 宫格内
        for ($_i = floor($i / 3) * 3; $_i < floor($i / 3) * 3 + 3; $_i++) {
            for ($_j = floor($j / 3) * 3; $_j < floor($j / 3) * 3 + 3; $_j++) {
                if ($sudoku[$_i][$_j] == $num) return false;
            }
        }
        return true;
    }

    private function next_i($i, $j) {
    if ($i == 8 && $j == 8) {
    throw new \Exception("跑完了");
    } else if ($i == 8) {
        return 0;
    } else {
        return $i + 1;
    }
    }

    private function next_j($i, $j) {
        if ($i == 8 && $j == 8) {
            throw new \Exception("跑完了");
        } else if ($i == 8) {
            return $j + 1;
        } else {
            return $j;
        }
    }

    private $sudoku = [
        [0, 0, 0, 0, 0, 0, 0, 2, 0],
        [0, 8, 0, 3, 0, 0, 7, 0, 1],
        [0, 6, 0, 8, 2, 0, 0, 5, 0],
        // --------------------------
        [0, 0, 0, 7, 0, 0, 0, 0, 0],
        [0, 0, 5, 0, 0, 0, 9, 0, 4],
        [0, 0, 0, 0, 9, 5, 0, 6, 0],
        // --------------------------
        [0, 0, 0, 0, 0, 3, 0, 0, 2],
        [0, 0, 0, 0, 1, 7, 8, 0, 0],
        [4, 0, 3, 0, 0, 0, 0, 0, 5],
    ];

    private function print_sudoku(){
        for ($i = 0; $i < count($this->sudoku); $i++) {
            if  ($i % 3 == 0) {
                echo "—————————" . "<br>";
            }
            for ($j = 0; $j < count($this->sudoku[$i]); $j++) {
                if ($j % 3 == 0) {
                    echo "|";
                }
                echo " " . $this->sudoku[$i][$j] . " ";
            }
            echo "|" . "<br>";
        }
        echo "—————————" . "<br>";
    }
}

(new Sudoku())->run();
