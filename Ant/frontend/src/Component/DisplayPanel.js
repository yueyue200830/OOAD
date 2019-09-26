import React from 'react';
import axios from 'axios';
import './DisplayPanel.css';
import { Button } from "antd";

class DisplayPanel extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      stickLength: 100,
      antPosition: [50],
      antColor: ['#80D8FF', '#B388FF', '#F48FB1', '#CCFF90', '#FF80AB', '#8C9EFF', '#FF9E80', '#FFCC80'],
      minTime: 0,
      maxTime: 0,
      minTimeDirection: 'left',
      maxTimeDirection: 'right',
      gameStatus: false,
      receiveData: false,
    };
  }

  setAntPosition = (ant, index) => {
    let pos = this.state.antPosition[index] / this.state.stickLength * 350;
    let color = this.state.antColor[index];
    if (pos > 0 + 0.0000000001 && pos < 350 - 0.0000000001) {
      return(
        <div key={index} className="Display-panel-ant"
             style={{ left: pos, backgroundColor: color }}/>
      );
    }
  }

  getGameData = () => {
    axios.post("http://127.0.0.1:8080/getGameData"
    ).then(
      res => {
        console.log("Game data get!");
        this.setState({
          receiveData: true,
          minTime: res.data.minTime[0],
          maxTime: res.data.maxTime[0],
          minTimeDirection: res.data.minTimeDirection,
          maxTimeDirection: res.data.maxTimeDirection,
          antPosition: res.data.position[0],
          stickLength: res.data.stickLength,
        });
      }
    );
  };

  updatePosition = () => {
    axios.post("http://127.0.0.1:8080/getPosition"
    ).then(
      res => {
        let gameOver = res.data.gameOver;
        if (gameOver == "true") {
          clearInterval(this.intervalId);
          this.setState({
            gameStatus: false,
            antPosition: res.data.position[0],
          });
        } else {
          this.setState({antPosition: res.data.position[0]});
        }
        this.forceUpdate();
      }
    );
  }

  startMinGame = () => {
    this.setState({gameStatus: true}, () => {
      axios.post("http://127.0.0.1:8080/startGame", {direction: 'min'}
      ).then(
        res => {
          console.log("Maximum time game start");
          this.intervalId = setInterval(this.updatePosition, 10);
        }
      )
    });
  };

  startMaxGame = () => {
    this.setState({gameStatus: true}, () => {
      axios.post("http://127.0.0.1:8080/startGame", {direction: 'max'}
      ).then(
        res => {
          console.log("Minimum time game start");
          this.intervalId = setInterval(this.updatePosition, 10);
        }
      )
    });
  };

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
              Minimum time cost is: {this.state.minTime}s.
            </span>
            <span>
              Ants' directions are: {this.state.minTimeDirection}.
            </span>
            <span>
              Maximum time cost is: {this.state.maxTime}s.
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
