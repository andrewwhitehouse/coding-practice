import React, {useEffect, useState} from 'react';

export default function User(props) {
  const [user, setUser] = useState(null);

  async function fetchUserData(id) {
    const response = await fetch("/" + id);
    const json = await response.json();
    setUser(json.data);
  }

  useEffect(() => {
    fetchUserData(props.id);
  }, [props.id]);

  if (!user) {
    return "loading ...";
  }

  return (
    <details>
      <summary>{user.name}</summary>
      <strong>{user.age}</strong>
      years old
      <br/>
      lives in {user.address}
    </details>
  );
}