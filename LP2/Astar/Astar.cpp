#include <iostream>
#include <vector>
using namespace std;

pair<int, int> findPosition(vector<vector<int>> initial_state) {
    pair<int, int> p;
    for(int i = 0; i < 3; i++) {
        for(int j = 0; j < 3; j++) {
            if(initial_state[i][j] == -1){
                p = make_pair(i, j);
                return p;
            }
        }
    }
}

int cal_heuristic(vector<vector<int>> arr, vector<vector<int>> final_state) {
    int heuristic = 0;
    
    for(int i = 0; i < 3; i++) {
        for(int j = 0; j < 3; j++) {
            if(arr[i][j] != final_state[i][j]) {
                heuristic++;
            }
        }
    }

    return heuristic;
}

void shift_up(vector<vector<int>> initial_state, vector<vector<int>> &up, int pos_row, int pos_col) {
    up = initial_state;
    int temp = up[pos_row][pos_col];
    up[pos_row][pos_col] = up[pos_row-1][pos_col];
    up[pos_row-1][pos_col] = temp;
}

void shift_down(vector<vector<int>> initial_state, vector<vector<int>> &down, int pos_row, int pos_col) {
    down = initial_state;
    int temp = down[pos_row][pos_col];
    down[pos_row][pos_col] = down[pos_row+1][pos_col];
    down[pos_row+1][pos_col] = temp;
}

void shift_left(vector<vector<int>> initial_state, vector<vector<int>> &left, int pos_row, int pos_col) {
    left = initial_state;
    int temp = left[pos_row][pos_col];
    left[pos_row][pos_col] = left[pos_row][pos_col-1];
    left[pos_row][pos_col-1] = temp;
}

void shift_right(vector<vector<int>> initial_state, vector<vector<int>> &right, int pos_row, int pos_col) {
    right = initial_state;
    int temp = right[pos_row][pos_col];
    right[pos_row][pos_col] = right[pos_row][pos_col+1];
    right[pos_row][pos_col+1] = temp;
}

void puzzle(vector<vector<int>> initial_state, vector<vector<int>> final_state) {
    if(initial_state == final_state) {
        return;
    }

    pair<int, int> p = findPosition(initial_state);
    int pos_row = p.first;
    int pos_col = p.second;

    vector<vector<int>> up;
    vector<vector<int>> down;
    vector<vector<int>> left;
    vector<vector<int>> right;

    int u_heuristic = 999999, d_heuristic = 999999, l_heuristic = 999999, r_heuristic = 999999;

    if(pos_row > 0) {
        shift_up(initial_state, up, pos_row, pos_col);
        u_heuristic = cal_heuristic(up, final_state);
    }
    if(pos_row < 2){
        shift_down(initial_state, down, pos_row, pos_col);
        d_heuristic = cal_heuristic(down, final_state);
    }
    if(pos_col > 0) {
        shift_left(initial_state, left, pos_row, pos_col);
        l_heuristic = cal_heuristic(left, final_state);
    } 
    if(pos_col < 2) {
        shift_right(initial_state, right, pos_row, pos_col);
        r_heuristic = cal_heuristic(right, final_state);
    }

    vector<int> heuristic = {u_heuristic, d_heuristic, l_heuristic, r_heuristic};
    int m_heuristic = 999999;
    int m_heuristic_idx;
    
    for(int i = 0; i < heuristic.size(); i++) {
        if(heuristic[i] < m_heuristic) {
            m_heuristic = heuristic[i];
            m_heuristic_idx = i;
        }
    }

    if(m_heuristic_idx == 0) {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                cout<<up[i][j]<<" ";
            }
            cout<<endl;
        }
        cout<<endl;
        puzzle(up, final_state);
        cout<<endl;
    } else if(m_heuristic_idx == 1) {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                cout<<down[i][j]<<" ";
            }
            cout<<endl;
        }
        cout<<endl;
        puzzle(down, final_state);
        cout<<endl;
    } else if(m_heuristic_idx == 2) {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                cout<<left[i][j]<<" ";
            }
            cout<<endl;
        }
        cout<<endl;
        puzzle(left, final_state);
        cout<<endl;
    } else if(m_heuristic_idx == 3) {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                cout<<right[i][j]<<" ";
            }
            cout<<endl;
        }
        cout<<endl;
        puzzle(right, final_state);
        cout<<endl;
    }
}

int main() {
    vector<vector<int>> initial_state = {
        {1, 2, 3},
        {4, 5, 6},
        {7, 8, -1}
    };

    vector<vector<int>> final_state = {
        {1, 2, 3},
        {-1, 4, 6},
        {7, 5, 8}
    };

    puzzle(initial_state, final_state);

    return 0;
}