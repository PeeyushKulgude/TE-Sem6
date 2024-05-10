#include <iostream>
#include <unordered_map>
#include <vector>
#include <set>
#include <limits.h>
using namespace std;

//Time complexity: O(N^2) 
class solution
{
public:
    unordered_map<int, vector<pair<int, int>>> adjlist;
    unordered_map<int, bool> visited;

    int nodes;
    int edges;
    solution()
    {
        nodes = 0;
        edges = 0;
    }

    void input()
    {

        cout << "Enter total number of nodes : ";
        cin >> nodes;
        cout << "Enter the total number of edges : ";
        cin >> edges;
        int u, v;
        int w;
        for (int i = 0; i < edges; i++)
        {
            cout << "Enter edge " << i << " ";
            cin >> u >> v;
            cout << "enter weight: ";
            cin >> w;
            adjlist[u].push_back(make_pair(v, w));
            adjlist[v].push_back(make_pair(u, w));
        }
    }

    void print()
    {
        for (auto i : adjlist)
        {
            cout << i.first << "---->";
            for (auto j : i.second)
            {
                cout << "[" << j.first << "," << j.second << "]"
                     << " ";
            }
            cout << endl;
        }
    }

    vector<int> djikstras(int source)
    {
        vector<int> distance(nodes, INT_MAX);

        distance[source] = 0;
        set<pair<int, int>> st;
        st.insert(make_pair(0, source));

        while (!st.empty())
        {
            auto top = *(st.begin());
            int frontnode = top.second;
            int weight = top.first;
            st.erase(top);

            for (auto i : adjlist[frontnode])
            {
                if (weight + i.second < distance[i.first])
                {
                    auto f = st.find(make_pair(distance[i.first], i.first));
                    if (f != st.end())
                    {
                        st.erase(f);
                    }

                    distance[i.first] = weight + i.second;
                    st.insert(make_pair(distance[i.first], i.first));
                }
            }
        }
        return distance;
    }
};

int main()
{
    solution s;
    s.input();
    s.print();
    cout << endl;
    vector<int> ans;
    ans = s.djikstras(0);
    for (int i = 0; i < ans.size(); i++)
    {
        cout << ans[i] << " ";
    }
}