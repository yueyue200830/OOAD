import React from 'react';
import axios from 'axios';
import './DisplayPanel.css';

class DisplayPanel extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      stickLength: 100,
      antPosition: [10, 20, 40, 80],
      antColor: ['#FFCC80', '#29B6F6', '#BA68C8', '#F48FB1'],
    };
  }

  setAntPosition = (ant, index) => {
    let pos = this.state.antPosition[index] / this.state.stickLength * 350;
    let color = this.state.antColor[index];
    return(
      <div className="Display-panel-ant"
           style={{ left: pos, backgroundColor: color }}/>
    )
  }

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
      </div>
    );
  }
}

export default DisplayPanel;
