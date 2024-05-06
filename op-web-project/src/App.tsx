import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Login from './components/login/Login';
import CallAuthorize from './components/callauthorize/CallAuthorize';

function App() {
  return (
    <div>
      <BrowserRouter>
        <div>
          <Routes>
            <Route path="/login" element={<Login />} />
            <Route path="/redirect/authorize" element={<CallAuthorize />} />
          </Routes>
        </div>
      </BrowserRouter>
    </div>
  );
}

export default App;
