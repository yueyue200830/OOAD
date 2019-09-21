import React from 'react';

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
      <div>
        <a>Hello</a>
      </div>
    );
  }
}

export default DisplayPanel;
