import React from 'react';
import DisplayPanel from './Component/DisplayPanel';
import SettingPanel from './Component/SettingPanel';
import { Layout } from 'antd';
import './App.css';

const { Header, Content, Footer } = Layout;

class App extends React.Component {
  render() {
    return (
      <Layout className="App">
        <Header theme="dark" className="App-header">
          Ant
        </Header>
        <Content className="App-content">
          <div className="Setting-panel">
            <SettingPanel />
          </div>
          <div className="App-line" />
          <div className="Display-panel">
            <DisplayPanel />
          </div>
        </Content>
        <Footer className="App-footer">
          OOAD Homework 1 by Jiayi Zhu & Yiqing Tao
        </Footer>
      </Layout>
    );
  }
}

export default App;
