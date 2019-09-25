import React from 'react';
import axios from 'axios';
import './DisplayPanel.css';
import { Button } from "antd";

class DisplayPanel extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      stickLength: 100,
      antPosition: [1, 20, 40, 80],
      antColor: ['#FFCC80', '#29B6F6', '#BA68C8', '#F48FB1'],
      minTime: 0,
      maxTime: 0,
      minTimeDirection: 'left, left, right, right',
      maxTimeDirection: 'right, right, right, right',
      gameStatus: false,
      receiveData: false,
    };
  }

  setAntPosition = (ant, index) => {
    let pos = this.state.antPosition[index] / this.state.stickLength * 350;
    let color = this.state.antColor[index];
    if (pos > 0 && pos < 350) {
      return(
        <div key={index} className="Display-panel-ant"
             style={{ left: pos, backgroundColor: color }}/>
      );
    }
  }

  getGameData = () => {
    console.log("called!");
    axios.post('http://127.0.0.1:8080' + "/getGameData"
    ).then(
        res => {
            console.log("Data get!");
            console.log(res);
            this.setState({receiveData: true});
        }
    )
  };

  getPosition = () => {
    axios.post('http://127.0.0.1:8080' + "/getPosition"
    ).then(
        res => {
            console.log(res);
            this.setState({gameStatus: false});
        }
    )
  }

  // 需要解决异步调用问题
  startMinGame = () => {
    this.setState({gameStatus: true});
    console.log(this.state.gameStatus);
    while (this.state.gameStatus) {
      this.getPosition();
    }
    console.log("finish");
  };

  startMaxGame = () => {};

  render() {
    let ant = this.state.antPosition;
    return (
      <div className="Display-panel-container">
        <div className="Display-panel-title">
          Ant Animation
        </div>
        <div className="animation">
          <div className="Ant-container">
            {ant.map(this.setAntPosition)}
          </div>
          <div className="Display-panel-stick" />
        </div>
        <div className="Display-panel-start"
             style={{visibility: this.state.receiveData?'visible':'hidden'}}>
          <div className="Display-panel-text">
            <span>
              Minimum time cost is: {this.state.minTime}.
            </span>
            <span>
              Ants' directions are: {this.state.minTimeDirection}.
            </span>
            <span>
              Maximum time cost is: {this.state.maxTime}.
            </span>
            <span>
              Ants' directions are: {this.state.maxTimeDirection}.
            </span>
          </div>
          <div className="Display-panel-button-setting">
            <Button className="Display-panel-button"
                    onClick={this.startMinGame}
                    disabled={this.state.gameStatus} >
              Play
            </Button>
            <Button className="Display-panel-button"
                    onClick={this.startMaxGame}
                    disabled={this.state.gameStatus} >
              Play
            </Button>
          </div>
        </div>
        <Button className="Display-panel-button"
                onClick={this.getGameData}>
          Test
        </Button>
        <div className="Display-panel-bottom" />
      </div>
    );
  }
}

export default DisplayPanel;
