import { BrowserRouter, Route, Routes } from 'react-router-dom';
import LoginTop from './components/login/Login';

function App() {
  return (
    <div>
      <BrowserRouter>
        <div>
          <Routes>
            <Route path="/login" element={<LoginTop />} />
          </Routes>
        </div>
      </BrowserRouter>
    </div>
  );
}

export default App;
