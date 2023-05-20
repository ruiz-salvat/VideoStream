import Layout from '../components/layout'
import CategoryContainer from '../components/index/category_container'

export default function HomePage({ categories, videos }) {
   return (
      <Layout>
            <div>
               {categories.map((category) => (
                  <CategoryContainer key={category.id} category={category} videos={videos.filter(video => video.category === category.id)}/>
               ))}
            </div>
      </Layout>
    )
}

export async function getStaticProps() {
   const categoriesRes = await fetch(`${process.env.NEXT_PUBLIC_API_URL}category/all`)
   const videosRes = await fetch(`${process.env.NEXT_PUBLIC_API_URL}video/all`)
   const categories = await categoriesRes.json()
   const videos = await videosRes.json()

   return {
      props: {
         categories,
         videos,
      }
   }
}