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
      <div className="container">
        <div className="title">
          Ant Animation
        </div>
        <div className="animation">
          <div className="Ant-container">
            <div className="ant" />
          </div>
          <div className="stick" />
        </div>
      </div>
    );
  }
}

export default DisplayPanel;
