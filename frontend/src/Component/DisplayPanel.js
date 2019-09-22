import React from 'react';
import './DisplayPanel.css';

class DisplayPanel extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      numberAnt: 1,
      antVelocity: [10]
    };
  }

  render() {
    return (
      <div className="Display-panel-container">
        <div className="Display-panel-title">
          Ant Animation
        </div>
        <div className="animation">
          <div className="Ant-container">
            <div className="Display-panel-ant" />
          </div>
          <div className="Display-panel-stick" />
        </div>
      </div>
    );
  }
}

export default DisplayPanel;
